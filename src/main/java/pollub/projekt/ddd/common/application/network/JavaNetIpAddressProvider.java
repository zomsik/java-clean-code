package pollub.projekt.ddd.common.application.network;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@Component
@Primary
@Slf4j
class JavaNetIpAddressProvider implements IpAddressProvider {

    InetAddress getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    @Override
    public Optional<String> getIpAddress() {
        try {

            return Optional.of(this.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            log.error("Unknown Host. Cannot get IPv4 address");
            return Optional.empty();
        }
    }
}
