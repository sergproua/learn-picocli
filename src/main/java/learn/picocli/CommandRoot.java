package learn.picocli;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.HashSet;
import java.util.Set;

@CommandLine.Command(name = "app",subcommandsRepeatable = true)
@Component
public class CommandRoot {
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec; // injected by picocli

    Set<String> executed = new HashSet<>();

    boolean validateOnlyOnce(String command) {
        if (executed.contains(command)) {
            // choose one of the below
            throw new CommandLine.ParameterException(spec.commandLine(), command + " can only be specified once"); // abort
        }
        executed.add(command);
        return true;
    }
}
