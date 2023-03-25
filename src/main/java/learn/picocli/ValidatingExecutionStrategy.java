package learn.picocli;

import picocli.CommandLine;

import java.util.List;

public class ValidatingExecutionStrategy implements CommandLine.IExecutionStrategy {
    @Override
    public int execute(CommandLine.ParseResult parseResult) {
        validate(parseResult);

        // default execution strategy
        // return new CommandLine.RunLast().execute(parseResult);

        // async execution strategy
        parseResult.asCommandLineList()
                .stream()
                // Exclude app level command (root)
                .filter(commandLine -> !commandLine.getCommandSpec().name().equals("app"))
                .parallel()
                .forEach(commandLine -> new CommandLine.RunLast().execute(commandLine.getParseResult()));

        return 0;
    }

    void validate(CommandLine.ParseResult parseResult) {
        List<CommandLine> all = parseResult.asCommandLineList();
        if (containsDuplicate(all))
            throw new CommandLine.ParameterException(parseResult.commandSpec().commandLine(), "duplicate repeatable subcommand");
    }

    boolean containsDuplicate(List<CommandLine> all) {
        return all.stream().map(spec -> spec.getCommandSpec().name()).distinct().count() != all.size();
    }
}