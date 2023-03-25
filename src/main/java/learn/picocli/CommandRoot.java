package learn.picocli;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(name = "app")
@Component
public class CommandRoot {
}
