package com.mcjty.rftools.blocks.storagemonitor;

import com.mcjty.container.EmptyContainer;
import com.mcjty.container.GenericContainerBlock;
import com.mcjty.rftools.RFTools;
import com.mcjty.rftools.blocks.Infusable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class StorageScannerBlock extends GenericContainerBlock implements Infusable {

    public StorageScannerBlock() {
        super(Material.iron, StorageScannerTileEntity.class);
        setBlockName("storageScannerBlock");
        setCreativeTab(RFTools.tabRfTools);
    }

    @Override
    public String getIdentifyingIconName() {
        return "machineStorageScanner";
    }

    @Override
    public int getGuiID() {
        return RFTools.GUI_STORAGE_SCANNER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiContainer createClientGui(EntityPlayer entityPlayer, TileEntity tileEntity) {
        StorageScannerTileEntity storageScannerTileEntity = (StorageScannerTileEntity) tileEntity;
        EmptyContainer storageScannerContainer = new EmptyContainer(entityPlayer);
        return new GuiStorageScanner(storageScannerTileEntity, storageScannerContainer);
    }
}
