package com.mcjty.rftools.blocks.shield;

import com.mcjty.rftools.blocks.shield.filters.ShieldFilter;
import com.mcjty.rftools.network.PacketRequestListFromServer;

import java.util.List;

public class PacketGetFilters extends PacketRequestListFromServer<ShieldFilter, PacketGetFilters, PacketFiltersReady> {

    public PacketGetFilters() {
    }

    public PacketGetFilters(int x, int y, int z) {
        super(x, y, z, ShieldTileEntity.CMD_GETFILTERS);
    }

    @Override
    protected PacketFiltersReady createMessageToClient(int x, int y, int z, List<ShieldFilter> result) {
        return new PacketFiltersReady(x, y, z, ShieldTileEntity.CLIENTCMD_GETFILTERS, result);
    }
}
