import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class Tutorial3 {

  public static void main(String[] args) throws IOException {
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    Screen screen = null;
    Terminal terminal = defaultTerminalFactory.createTerminal();
    screen = new TerminalScreen(terminal);
    screen.startScreen();
    screen.setCursorPosition(null);

    Random random = new SecureRandom();
    TerminalSize terminalSize = screen.getTerminalSize();
    for (int column = 0; column < terminalSize.getColumns(); column++) {
      for (int row = 0; row < terminalSize.getRows(); row++) {
        screen.setCharacter(column, row, new TextCharacter(
            ' ',
            ANSI.DEFAULT,
            TextColor.ANSI.values()[random.nextInt(ANSI.values().length)]
        ));
      }
    }
    screen.refresh();
  }
}
