package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        // MockitoExtension already initializes @Mock fields, no need for MockitoAnnotations.initMocks(this)
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");
        mapParser.parseMap(map);
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();
        Mockito.verify(levelFactory, Mockito.atLeastOnce()).createGhost();
    }

    /**
     * Test for the parseMap method (bad map).
     */
    /**
     * Test for the parseMap method (bad map).
     * This version keeps assertEquals for the exception message.
     */
    @Test
    public void testParseMapWrong1() {
        PacmanConfigurationException thrown = assertThrows(PacmanConfigurationException.class, () -> {
            // MockitoExtension already initializes @Mock fields
            assertNotNull(boardFactory);
            assertNotNull(levelFactory);
            MapParser mapParser = new MapParser(levelFactory, boardFactory);
            ArrayList<String> map = new ArrayList<>();
            // invalid map: rows have inconsistent lengths (should trigger parser validation)
            map.add("####");
            map.add("#P"); // shorter row -> invalid
            mapParser.parseMap(map);
        });

        // Keep assertEquals as requested.
        // Replace "Invalid map" with the exact message your MapParser actually throws.
        assertEquals("Invalid map", thrown.getMessage());
    }
}




