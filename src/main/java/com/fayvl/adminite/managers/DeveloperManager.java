package com.fayvl.adminite.managers;

import net.minecraft.client.MinecraftClient;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DeveloperManager {

    public static class Developer {
        private final String name;
        private final String id;
        private final boolean hasPermissions;

        public Developer(String name, String id, boolean hasPermissions) {
            this.name = name;
            this.id = id;
            this.hasPermissions = hasPermissions;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public boolean hasPermissions() {
            return hasPermissions;
        }
    }

    private static final Set<Developer> developers = new HashSet<>();

    static {
        developers.add(new Developer("Katt", "0eaab2ea-0fd3-42e2-a255-e41f6ffc9524", true));
        developers.add(new Developer("BigP", "5d8af060-129e-419c-b3ac-c0dad1c91181", true));
        developers.add(new Developer("BlackIlyKat", "cf9f9198-7150-442d-8dcf-2f8b90d0417f", false));
    }

    public static boolean isDeveloper(String senderUUID, String UUID) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            UUID playerUUID = client.player.getUuid();
            String playerUUIDString = playerUUID.toString();

            return developers.stream().anyMatch(dev ->
                    dev.getId().equals(senderUUID) || dev.getId().equals(UUID)
            );
        }
        return false;
    }

    public static boolean isAllowed() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            UUID playerUUID = client.player.getUuid();
            String playerUUIDString = playerUUID.toString();
            return developers.stream()
                    .filter(dev -> dev.getId().equals(playerUUIDString))
                    .anyMatch(Developer::hasPermissions);
        }
        return false;
    }

    public static Developer getDeveloper(String id) {
        return developers.stream()
                .filter(dev -> dev.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void renderBadge()
    {
        return;
    }
}
