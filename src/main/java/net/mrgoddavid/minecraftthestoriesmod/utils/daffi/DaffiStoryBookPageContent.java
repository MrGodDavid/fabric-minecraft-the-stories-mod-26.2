package net.mrgoddavid.minecraftthestoriesmod.utils.daffi;

import net.mrgoddavid.minecraftthestoriesmod.utils.directory.Directory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * @author Mr. GodDavid
 * @since 9/23/2026
 */
public final class DaffiStoryBookPageContent {

    private static final int WIDTH = 82;
    private static final int HEIGHT = 110;
    static final Directory PAINTED_STORYBOOK_PAGES_DIR = Directory.builder().src().main_dir().resources().assets().minecraft_the_stories_mod().textures().item().storybook_pages().build();
    static final Directory ATLAS_DIR = Directory.builder().src().main_dir().resources().assets().minecraft_the_stories_mod().textures().daffi().build();

    private static final String STORYBOOK_PAGE_PREFIX = "storybook_page_";
    private final Map<Integer, SGACharacter> sgaCharacterMap;
    private final List<DaffiTextProhibitRectangle> prohibitAreas = new ArrayList<>();
    private static final int LINE_HEIGHT = 6;
    private static final int CHARACTER_SPACING = 1;
    private static final int PROHIBIT_PADDING = 2;

    public DaffiStoryBookPageContent(Map<Integer, SGACharacter> sgaCharacterMap) {
        this.sgaCharacterMap = sgaCharacterMap;
    }

    public void addAllProhibitedAreas(DaffiTextProhibitRectangle... prohibitAreas) {
        if (areRectanglesValid(prohibitAreas)) {
            this.prohibitAreas.addAll(List.of(prohibitAreas));
        }
    }

    private boolean areRectanglesValid(DaffiTextProhibitRectangle... prohibitAreas) {
        for (DaffiTextProhibitRectangle prohibitArea : prohibitAreas) {
            if (prohibitArea.getX() < 0 || prohibitArea.getY() < 0 || prohibitArea.getWidth() < 0 || prohibitArea.getHeight() < 0
                    || prohibitArea.getX() + prohibitArea.getWidth() > WIDTH || prohibitArea.getY() + prohibitArea.getHeight() > HEIGHT) {
                System.err.println("Prohibited area " + prohibitArea + "exceeds the maximum size of page!");
                return false;
            }
        }
        return true;
    }

    public void paint(int page, String text) {
        try {
            File atlasFile = new File(ATLAS_DIR.toString().concat("daffi_font_atlas.png"));
            BufferedImage atlasImage = ImageIO.read(atlasFile);
            BufferedImage paintedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) paintedImage.getGraphics();

            for (PlacedCharacter character : layoutText(text)) {
                BufferedImage cropped = atlasImage.getSubimage(
                        (int) character.sgaCharacter().bounds().getX(),
                        (int) character.sgaCharacter().bounds().getY(),
                        (int) character.sgaCharacter().bounds().getWidth(),
                        (int) character.sgaCharacter().bounds().getHeight()
                );
                g2d.drawImage(cropped, character.x(), character.y(), null);
            }

            g2d.setColor(Color.RED);
            for (DaffiTextProhibitRectangle prohibitArea : prohibitAreas) {
                g2d.drawRect(
                        (int) prohibitArea.getX(), (int) prohibitArea.getY(),
                        (int) prohibitArea.getWidth(), (int) prohibitArea.getHeight()
                );
            }
            g2d.dispose();

            File paintedFile = new File(PAINTED_STORYBOOK_PAGES_DIR.toString().concat(STORYBOOK_PAGE_PREFIX).concat(String.valueOf(page)).concat(".png"));
            ImageIO.write(paintedImage, "png", paintedFile);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<PlacedCharacter> layoutText(String text) {
        List<PlacedCharacter> result = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String word : text.split("\\s+")) {
            List<SGACharacter> characters = translateFromString(word);
            if (characters.isEmpty()) {
                continue;
            }
            int wordLength = getWidth(characters);
            while (true) {
                List<Range> ranges = getAvailableRanges(y);
                Range range = findRange(ranges, x, wordLength);
                if (range != null) {
                    x = range.start();
                    for (SGACharacter character : characters) {
                        result.add(new PlacedCharacter(character, x, y));
                        x += getWidth(character) + 1;
                    }
                    x += getSpaceWidth();
                    break;
                }
                y += LINE_HEIGHT;
                x = 0;
                if (y + LINE_HEIGHT > HEIGHT) {
                    return result;
                }
                if (wordLength > WIDTH) {
                    placeCharacters(result, characters, x, y);
                    return result;
                }
            }
        }
        return result;
    }

    private Range findRange(List<Range> ranges, int x, int requiredLength) {
        for (Range range : ranges) {
            int start = Math.max(x, range.start());
            if (start + requiredLength <= range.end()) {
                return new Range(start, range.end());
            }
        }
        return null;
    }

    private List<Range> getAvailableRanges(int y) {
        List<Range> blocked = new ArrayList<>();
        for (DaffiTextProhibitRectangle area : prohibitAreas) {
            int areaX = (int) area.getX();
            int areaY = (int) area.getY();
            int areaWidth = (int) area.getWidth();
            int areaHeight = (int) area.getHeight();
            if (y < areaY + areaHeight && y + LINE_HEIGHT > areaY) {
                int start = Math.max(0, areaX - PROHIBIT_PADDING);
                int end = Math.min(WIDTH, areaX + areaWidth + PROHIBIT_PADDING);
                blocked.add(new Range(start, end));
            }
        }

        if (blocked.isEmpty()) {
            return List.of(new Range(0, WIDTH));
        }
        blocked.sort(Comparator.comparingInt(Range::start));

        List<Range> ranges = new ArrayList<>();
        int x = 0;
        for (Range area : blocked) {
            if (x < area.start()) {
                ranges.add(new Range(x, area.start()));
            }
            x = Math.max(x, area.end());
        }
        if (x < WIDTH) {
            ranges.add(new Range(x, WIDTH));
        }
        return ranges;
    }

    private void placeCharacters(List<PlacedCharacter> result, List<SGACharacter> characters, int x, int y) {
        for (SGACharacter character : characters) {
            if (x + getWidth(character) > WIDTH) {
                y += LINE_HEIGHT;
                x = 0;
            }
            if (y + LINE_HEIGHT > HEIGHT) {
                return;
            }
            result.add(new PlacedCharacter(character, x, y));
            x += getWidth(character) + CHARACTER_SPACING;
        }
    }

    private int getWidth(List<SGACharacter> characters) {
        int width = 0;
        for (int i = 0; i < characters.size(); i++) {
            width += getWidth(characters.get(i));
            if (i < characters.size() - 1) {
                width += CHARACTER_SPACING;
            }
        }
        return width;
    }

    private int getWidth(SGACharacter character) {
        return (int) character.bounds().getWidth();
    }

    private int getSpaceWidth() {
        return getWidth(SGACharacter.WHITESPACE) + CHARACTER_SPACING;
    }

    private List<SGACharacter> translateFromString(String word) {
        List<SGACharacter> characters = new ArrayList<>();
        for (char c : word.toUpperCase().toCharArray()) {
            if (c >= 48 && c <= 57) {
                characters.add(Optional.ofNullable(sgaCharacterMap.get(c - 48)).orElse(SGACharacter.EMPTY));
            } else if (c >= 65 && c <= 90) {
                characters.add(Optional.ofNullable(sgaCharacterMap.get(c - 29)).orElse(SGACharacter.EMPTY));
            }
        }
        return characters;
    }

    /**
     * @author Mr. GodDavid
     * @since 9/23/2026
     */
    private record Range(int start, int end) {
    }

    /**
     * @author Mr. GodDavid
     * @since 9/23/2026
     */
    private record PlacedCharacter(SGACharacter sgaCharacter, int x, int y) {
    }
}
