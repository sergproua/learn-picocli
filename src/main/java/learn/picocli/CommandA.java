package learn.picocli;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "A")
@Component
@Log4j2
public class CommandA implements Callable<Integer> {
    @CommandLine.Option(names = {"-r", "--required"}, description = "I am required", required = true)
    Long param;

    @Override
    public Integer call() throws InterruptedException {
        log.info("A command is running with {}", param);
        Thread.sleep(10000);
        log.info("A command has stopped");
        return 0;
    }
}
