import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TextColor.ANSI;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;
import java.io.IOException;

public class Demo {

  public static void main(String[] args) throws IOException, InterruptedException {

    Terminal terminal = new DefaultTerminalFactory().createTerminal();
    terminal.enterPrivateMode();
    terminal.clearScreen();
    terminal.setCursorVisible(false);

    Screen screen = new TerminalScreen(terminal);

    final TextGraphics tg = terminal.newTextGraphics();
    //screen.startScreen();
    //screen.readInput();
    //screen.stopScreen();
    //Write a text
    tg.setBackgroundColor(ANSI.WHITE);
    tg.setForegroundColor(ANSI.BLACK);
    tg.putString(2, 1, "Lanterna Tutorial 2 - Press ESC to exit", SGR.BOLD);
    tg.setForegroundColor(TextColor.ANSI.DEFAULT);
    tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
    tg.putString(5, 3, "Terminal Size: ", SGR.BOLD);
    tg.putString(5 + "Terminal Size: ".length(), 3, terminal.getTerminalSize().toString());
    terminal.flush();

    TerminalPosition startPosition = terminal.getCursorPosition();

    terminal.addResizeListener(new TerminalResizeListener() {
      @Override
      public void onResized(Terminal terminal, TerminalSize terminalSize) {
        tg.drawLine(5, 3, terminalSize.getColumns() - 1, 3, ' ');
        tg.putString(5, 3, "Terminal Size: ", SGR.BOLD);
        tg.putString(5 + "Terminal Size: ".length(), 3, terminalSize.toString());
      }
    });

    KeyStroke keystroke = terminal.readInput();

    while(keystroke.getKeyType() != KeyType.Escape) {
      tg.drawLine(5, 4, terminal.getTerminalSize().getColumns() - 1, 4, ' ');
      tg.putString(5, 4, "Last KeyStroke: ", SGR.BOLD);
      tg.putString(5 + "Last Keystroke: ".length(), 4, keystroke.toString());
      terminal.flush();
      keystroke = terminal.readInput();
    }

    //terminal.setCursorPosition(startPosition.withRelativeColumn(3).withRelativeRow(2));
    //terminal.resetColorAndSGR();
    //terminal.setCursorPosition(terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
    terminal.bell();
    terminal.close();
  }
}
