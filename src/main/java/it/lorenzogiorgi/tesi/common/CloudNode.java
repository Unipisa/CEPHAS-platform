package it.lorenzogiorgi.tesi.common;

import it.lorenzogiorgi.tesi.Orchestrator;
import it.lorenzogiorgi.tesi.dns.DNSManagement;

public class CloudNode extends ComputeNode{

    public void initialize() {
        cleanupContainer();
        if(!initializeFrontProxy()) {
            logger.fatal("Cloud Envoy node initialization failed");
            logger.fatal("Orchestrator will now exit");
            System.exit(1);
        }
        cleanupDanglingImages();

        //insert route for the Orchestrator (cluster already configured by static configuration)
        Orchestrator.envoyConfigurationServer.addPublicRouteToProxy("cloud", "/orchestrator", "orchestrator_cluster");

        //update DNS entry for Cloud Proxy
        if(DNSManagement.updateDNSRecord(Configuration.PLATFORM_DOMAIN, Configuration.PLATFORM_CLOUD_DOMAIN, "A",
                3600, this.getIpAddress())) {
            logger.info("DNS record for cloud node "+ id + " added to DNS Server");
        } else {
            logger.warn("Error during DNS record add/update for cloud node "+ id);
        }
    }
}
