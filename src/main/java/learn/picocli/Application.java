package learn.picocli;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import picocli.CommandLine;

@SpringBootApplication
@RequiredArgsConstructor
public class Application  implements CommandLineRunner, ExitCodeGenerator {
    final CommandLine.IFactory factory;
    final CommandRoot commandroot;
    final CommandA commandA;
    final CommandB commandB;
    private int exitCode;

    @Override
    public void run(String... args) {
        exitCode = new CommandLine(commandroot, factory)
                .addSubcommand(commandA)
                .addSubcommand(commandB)
                .setExecutionStrategy(new ValidatingExecutionStrategy())
                .execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public static void main(String[] args) {
        System.exit(
                SpringApplication.exit(
                        new SpringApplicationBuilder(Application.class)
                                .bannerMode(Banner.Mode.OFF)
                                .web(WebApplicationType.NONE)
                                .headless(false)
                                .run(args)
                )
        );
    }

}
