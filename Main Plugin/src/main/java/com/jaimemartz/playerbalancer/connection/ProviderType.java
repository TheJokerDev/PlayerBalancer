package com.jaimemartz.playerbalancer.connection;

import com.jaimemartz.playerbalancer.PlayerBalancer;
import com.jaimemartz.playerbalancer.connection.provider.AbstractProvider;
import com.jaimemartz.playerbalancer.connection.provider.types.*;
import com.jaimemartz.playerbalancer.section.ServerSection;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public enum ProviderType {
    NONE {
        NullProvider provider = new NullProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    RANDOM {
        RandomProvider provider = new RandomProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    LOWEST {
        LowestProvider provider = new LowestProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    BALANCED {
        BalancedProvider provider = new BalancedProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    PROGRESSIVE {
        ProgressiveProvider provider = new ProgressiveProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    FILLER {
        FillerProvider provider = new FillerProvider();

        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            return provider.requestTarget(plugin, section, servers, player);
        }
    },

    EXTERNAL {
        @Override
        public ServerInfo requestTarget(PlayerBalancer plugin, ServerSection section, List<ServerInfo> servers, ProxiedPlayer player) {
            AbstractProvider provider = section.getExternalProvider();
            if (provider == null) {
                plugin.getLogger().warning("Target requested to the EXTERNAL provider with the section not having a provider instance, falling back to RANDOM...");
                return RANDOM.requestTarget(plugin, section, servers, player);
            }
            return provider.requestTarget(plugin, section, servers, player);
        }
    };

    public abstract ServerInfo requestTarget(
            PlayerBalancer plugin,
            ServerSection section,
            List<ServerInfo> servers,
            ProxiedPlayer player
    );
}