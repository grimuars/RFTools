package com.mcjty.rftools.blocks.dimlets;

import com.mcjty.container.GenericContainerBlock;
import com.mcjty.rftools.RFTools;
import com.mcjty.rftools.blocks.BlockTools;
import com.mcjty.rftools.blocks.Infusable;
import com.mcjty.rftools.blocks.endergen.GuiPearlInjector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DimletResearcherBlock extends GenericContainerBlock implements Infusable {

    public DimletResearcherBlock() {
        super(Material.iron, DimletResearcherTileEntity.class);
        setBlockName("dimletResearcherBlock");
        setCreativeTab(RFTools.tabRfTools);
    }

    @Override
    public int getGuiID() {
        return RFTools.GUI_DIMLET_RESEARCHER;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        DimletResearcherTileEntity dimletResearcherTileEntity = (DimletResearcherTileEntity)world.getTileEntity(x, y, z);

        if (dimletResearcherTileEntity != null) {
            BlockTools.emptyInventoryInWorld(world, x, y, z, block, dimletResearcherTileEntity);
        }

        super.breakBlock(world, x, y, z, block, meta);
    }

    @Override
    protected void breakWithWrench(World world, int x, int y, int z) {
        DimletResearcherTileEntity dimletResearcherTileEntity = (DimletResearcherTileEntity)world.getTileEntity(x, y, z);

        if (dimletResearcherTileEntity != null) {
            for (int i = 0 ; i < dimletResearcherTileEntity.getSizeInventory() ; i++) {
                dimletResearcherTileEntity.setInventorySlotContents(i, null);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiContainer createClientGui(EntityPlayer entityPlayer, TileEntity tileEntity) {
        DimletResearcherTileEntity dimletResearcherTileEntity = (DimletResearcherTileEntity) tileEntity;
        DimletResearcherContainer dimletResearcherContainer = new DimletResearcherContainer(entityPlayer, dimletResearcherTileEntity);
        return new GuiDimletResearcher(dimletResearcherTileEntity, dimletResearcherContainer);
    }

    @Override
    public Container createServerContainer(EntityPlayer entityPlayer, TileEntity tileEntity) {
        return new DimletResearcherContainer(entityPlayer, (DimletResearcherTileEntity) tileEntity);
    }


    @Override
    public String getIdentifyingIconName() {
        return "machineDimletResearcher";
    }
}
