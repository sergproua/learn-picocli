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
    public Integer call() {
        log.info("Started A with {}", param);
        return 0;
    }
}