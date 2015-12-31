package net.reederhome.colin.mods.botanicalfactory;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;

public class BlockOpenChest extends Block implements ITileEntityProvider, ILexiconable {

    private IIcon iconTop;
    private IIcon iconBottom;
    public BlockOpenChest() {
        super(Material.wood);
        setBlockTextureName(BotanicalFactory.MODID+":openChest");
        setCreativeTab(BotanicalFactory.tab);
        setBlockName("openChest");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityOpenChest();
    }

    @Override
    public void registerBlockIcons(IIconRegister ir) {
        super.registerBlockIcons(ir);
        iconTop = ir.registerIcon(textureName+"Top");
        iconBottom = ir.registerIcon(textureName+"Bottom");
    }

    @Override
    public IIcon getIcon(int s, int m) {
        if(s == 1) {
            return iconTop;
        }
        else if(s == 0) {
            return iconBottom;
        }
        else {
            return blockIcon;
        }
    }

    @Override
    public void onNeighborBlockChange(World w, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(w, x, y, z, block);
        if(w.isBlockIndirectlyGettingPowered(x, y, z)) {
            if(w.getBlockMetadata(x, y, z) == 0) {
                ((TileEntityOpenChest)w.getTileEntity(x, y, z)).activate();
                w.setBlockMetadataWithNotify(x, y, z, 1, 3);
            }
        }
        else {
            w.setBlockMetadataWithNotify(x, y, z, 0, 3);
        }
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        p.displayGUIChest((IInventory) w.getTileEntity(x, y, z));
        return super.onBlockActivated(w, x, y, z, p, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return BotanicalFactory.entryOpenChest;
    }
}
