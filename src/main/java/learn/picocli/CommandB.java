package learn.picocli;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "B")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Log4j2
public class CommandB implements Callable<Integer> {
    @CommandLine.Option(names = {"-r", "--required"}, description = "I am required", required = true)
    Long param;

    @Override
    public Integer call() throws InterruptedException {
        log.info("B command is running with {}", param);
        Thread.sleep(1000);
        log.info("B command has stopped");
        return 0;
    }
}
