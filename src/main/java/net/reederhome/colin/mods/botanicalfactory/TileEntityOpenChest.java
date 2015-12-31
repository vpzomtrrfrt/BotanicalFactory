package net.reederhome.colin.mods.botanicalfactory;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOpenChest extends TileEntity implements IInventory {

    private ItemStack[] slots = new ItemStack[27];

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return slots[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int i1) {
        if(slots[i] == null || slots[i].stackSize <= i1) {
            ItemStack tr = slots[i];
            slots[i] = null;
            return tr;
        }
        else {
            slots[i].stackSize -= i1;
            ItemStack tr = slots[i].copy();
            tr.stackSize = i1;
            return tr;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        return getStackInSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        slots[i] = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "tile.openChest.name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if(nbt.hasKey("Items")) {
            NBTTagList l = (NBTTagList) nbt.getTag("Items");
            for(int i = 0; i < l.tagCount(); i++) {
                NBTTagCompound item = l.getCompoundTagAt(i);
                slots[item.getInteger("Slot")] = ItemStack.loadItemStackFromNBT(item);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList l = new NBTTagList();
        for(int i = 0; i < getSizeInventory(); i++) {
            if(slots[i] != null) {
                NBTTagCompound item = new NBTTagCompound();
                slots[i].writeToNBT(item);
                item.setInteger("Slot", i);
                l.appendTag(item);
            }
        }
        nbt.setTag("Items", l);
    }

    public void activate() {
        for(int i = 0; i < getSizeInventory(); i++) {
            ItemStack td = decrStackSize(i, 1);
            if(td != null) {
                EntityItem e = new EntityItem(worldObj, xCoord+0.5, yCoord-0.25, zCoord+0.5, td);
                e.motionX = 0;
                e.motionZ = 0;
                worldObj.spawnEntityInWorld(e);
            }
        }
    }
}
