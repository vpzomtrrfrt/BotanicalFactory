package net.reederhome.colin.mods.botanicalfactory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FactoryCreativeTab extends CreativeTabs {

    private List extraItems = new ArrayList();

    public FactoryCreativeTab() {
        super(BotanicalFactory.MODID);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BotanicalFactory.openChest);
    }

    @Override
    public void displayAllReleventItems(List l) {
        super.displayAllReleventItems(l);
        l.addAll(extraItems);
    }

    public void addStack(ItemStack s) {
        extraItems.add(s);
    }
}
