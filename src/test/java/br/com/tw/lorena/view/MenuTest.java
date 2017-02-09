package br.com.tw.lorena.view;

import br.com.tw.lorena.controller.CalculaterPathBetweenTwoNodesController;
import br.com.tw.lorena.controller.DeephtSearchLimitedController;
import br.com.tw.lorena.controller.DijkistraController;
import br.com.tw.lorena.model.Town;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

    private Menu menu;

    @Mock private Printer printer;
    @Mock private CalculaterPathBetweenTwoNodesController calculaterPathBetweenTwoNodesController;
    @Mock private DijkistraController dijkistraController;
    @Mock private DeephtSearchLimitedController deephtSearchLimitedController;

    @Before
    public void setUp(){
        menu = new Menu(calculaterPathBetweenTwoNodesController,
                dijkistraController,
                deephtSearchLimitedController,
                        printer);
    }

    @Test
    public void shouldShowMenu() throws IOException {
        menu.outputMenuOption();
        verify(printer, times(1)).printMenu();
    }

    @Test
    public void shouldExecuteMenuOptionOne() throws IOException {
        Town townA = new Town("A");
        Town townB = new Town("B");
        when(calculaterPathBetweenTwoNodesController.getOption()).thenReturn(1);
        menu.executeMenuOption(new Input(1, Arrays.asList(townA, townB), null));
        verify(calculaterPathBetweenTwoNodesController, times(1)).setTowns(Arrays.asList(townA, townB));
        verify(calculaterPathBetweenTwoNodesController, times(1)).execute();
    }

    @Test
    public void shouldExecuteMenuOptionTwo() throws IOException {
        Town townA = new Town("A");
        Town townB = new Town("B");
        when(dijkistraController.getOption()).thenReturn(2);
        menu.executeMenuOption(new Input(2, Arrays.asList(townA, townB), null));
        verify(dijkistraController, times(1)).setTowns(Arrays.asList(townA, townB));
        verify(dijkistraController, times(1)).execute();
    }

    @Test
    public void shouldExecuteMenuOptionThree() throws IOException {
        Town townA = new Town("A");
        Town townB = new Town("B");
        when(deephtSearchLimitedController.getOption()).thenReturn(3);
        menu.executeMenuOption(new Input(3, Arrays.asList(townA, townB), "stop < 3"));
        verify(deephtSearchLimitedController, times(1)).setTowns(Arrays.asList(townA, townB));
        verify(deephtSearchLimitedController, times(1)).setCondition("stop < 3");
        verify(deephtSearchLimitedController, times(1)).execute();
    }

    @Test
    public void shouldExecuteMenuOptionFour() throws IOException {
        Town townA = new Town("A");
        Town townB = new Town("B");
        Town townC = new Town("C");
        when(deephtSearchLimitedController.getOption()).thenReturn(4);
        menu.executeMenuOption(new Input(4, Arrays.asList(townA, townB, townC), "dist < 4"));
        verify(deephtSearchLimitedController, times(1)).setTowns(Arrays.asList(townA, townB, townC));
        verify(deephtSearchLimitedController, times(1)).setCondition("dist < 4");
        verify(deephtSearchLimitedController, times(1)).execute();
    }
}
