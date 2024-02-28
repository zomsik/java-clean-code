package pollub.projekt.ddd.common.application.network;

import java.util.Optional;

public interface IpAddressProvider {

    Optional<String> getIpAddress();
}
