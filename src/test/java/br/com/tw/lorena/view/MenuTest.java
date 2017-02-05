package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.CalculaterPathBetweenTwoNodesAlgorithms;
import br.com.tw.lorena.controller.DeephtSearchLimitedAlgorithms;
import br.com.tw.lorena.controller.DijkistraAlgorithms;
import br.com.tw.lorena.model.Town;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    private Menu menu;

    @Mock private Printer printer;
    @Mock private CalculaterPathBetweenTwoNodesAlgorithms calculaterPathBetweenTwoNodesAlgorithms;
    @Mock private DijkistraAlgorithms dijkistraAlgorithms;
    @Mock private DeephtSearchLimitedAlgorithms deephtSearchLimitedAlgorithms;

    @Before
    public void setUp(){
        menu = new Menu(calculaterPathBetweenTwoNodesAlgorithms,
                        dijkistraAlgorithms,
                        deephtSearchLimitedAlgorithms,
                        printer);
    }

    @Test
    public void shouldShowMenu() throws IOException {
        menu.showMenuOption();
        verify(printer, times(1)).printMenu();
    }


    @Test
    public void shouldGetTheMenuOption() throws IOException {
        int option = menu.getMenuOption("1");
        assertEquals(option, 1);
    }

    @Test
    public void shouldExitOfProgram() throws IOException {
        int option = menu.getMenuOption("A");
        assertEquals(option, 5);
    }

    @Test
    public void shouldExecuteMenuOption() throws IOException {
        Town townA = new Town("A");
        Town townB = new Town("B");
        menu.executeMenuOption(1, Arrays.asList(townA, townB));
        verify(calculaterPathBetweenTwoNodesAlgorithms, times(1)).setTowns(Arrays.asList(townA, townB));
        verify(calculaterPathBetweenTwoNodesAlgorithms, times(1)).execute();
    }
}
