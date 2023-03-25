package learn.picocli;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(name = CommandRoot.RootCommandName, subcommandsRepeatable = true)
@Component
public class CommandRoot {
    public static final String RootCommandName = "app";
}
