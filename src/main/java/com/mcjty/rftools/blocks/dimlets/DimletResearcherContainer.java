package com.mcjty.rftools.blocks.dimlets;

import com.mcjty.container.ContainerFactory;
import com.mcjty.container.GenericContainer;
import com.mcjty.container.SlotDefinition;
import com.mcjty.container.SlotType;
import com.mcjty.rftools.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class DimletResearcherContainer extends GenericContainer {
    public static final String CONTAINER_INVENTORY = "container";

    public static final int SLOT_DIMLETINPUT = 0;
    public static final int SLOT_DIMLETOUTPUT = 1;

    public static final ContainerFactory factory = new ContainerFactory() {
        @Override
        protected void setup() {
            addSlotBox(new SlotDefinition(SlotType.SLOT_SPECIFICITEM, new ItemStack(ModItems.unknownDimlet)), CONTAINER_INVENTORY, SLOT_DIMLETINPUT, 64, 24, 1, 18, 1, 18);
            addSlotBox(new SlotDefinition(SlotType.SLOT_OUTPUT), CONTAINER_INVENTORY, SLOT_DIMLETOUTPUT, 118, 24, 1, 18, 1, 18);
            layoutPlayerInventorySlots(10, 70);
        }
    };

    public DimletResearcherContainer(EntityPlayer player, DimletResearcherTileEntity containerInventory) {
        super(factory, player);
        addInventory(CONTAINER_INVENTORY, containerInventory);
        addInventory(ContainerFactory.CONTAINER_PLAYER, player.inventory);
        generateSlots();
    }
}
