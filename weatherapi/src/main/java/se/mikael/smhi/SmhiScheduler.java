package se.mikael.smhi;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.mikael.service.SmhiService;

@ApplicationScoped
public class SmhiScheduler {

    @Inject
    SmhiService smhiService;

    @Scheduled(every = "15m")
    void periodicUpdate() {
        smhiService.fetchAndStore(58.39, 13.85);
    }
}
