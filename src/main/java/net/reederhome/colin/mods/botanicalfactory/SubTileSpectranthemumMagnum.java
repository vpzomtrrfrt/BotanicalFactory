package net.reederhome.colin.mods.botanicalfactory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.Iterator;
import java.util.List;

public class SubTileSpectranthemumMagnum extends SubTileFunctional {

    private static final String TAG_BIND_X = "bindX";
    private static final String TAG_BIND_Y = "bindY";
    private static final String TAG_BIND_Z = "bindZ";

    private static final int COST = 1000;
    private static final double RANGE = 0.5;
    private static final int BIND_RANGE = 18;
    private static final int DELAY = 20;

    int bindX, bindY = -1, bindZ;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(ticksExisted % DELAY == 0 && redstoneSignal == 0 && supertile.getWorldObj().blockExists(bindX, bindY, bindZ)) {
            int x = supertile.xCoord;
            int y = supertile.yCoord;
            int z = supertile.zCoord;

            boolean did = false;
            List<EntityLivingBase> ents = supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(x - RANGE, y - RANGE, z - RANGE, x + RANGE + 1, y + RANGE + 1, z + RANGE + 1));
            Iterator<EntityLivingBase> it = ents.iterator();
            while(it.hasNext() && !did && mana >= COST) {
                EntityLivingBase ent = it.next();
                spawnExplosionParticles(ent);
                ent.setPositionAndUpdate(bindX+0.5, bindY+1.5, bindZ+0.5);
                spawnExplosionParticles(ent);
                mana -= COST;
                did = true;
            }
            if(did && !supertile.getWorldObj().isRemote) {
                sync();
            }
        }
    }

    public static void spawnExplosionParticles(EntityLivingBase ent) {
        for(int i = 0; i < 10; i++) {
            double m = 0.01;
            double d0 = ent.worldObj.rand.nextGaussian() * m;
            double d1 = ent.worldObj.rand.nextGaussian() * m;
            double d2 = ent.worldObj.rand.nextGaussian() * m;
            double d3 = 10.0D;
            ent.worldObj.spawnParticle("explode", ent.posX + ent.worldObj.rand.nextFloat() * ent.width * 2.0F - ent.width - d0 * d3, ent.posY + ent.worldObj.rand.nextFloat() * ent.height - d1 * d3, ent.posZ + ent.worldObj.rand.nextFloat() * ent.width * 2.0F - ent.width - d2 * d3, d0, d1, d2);
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 0x98BCFF;
    }

    @Override
    public int getMaxMana() {
        return 16000;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        boolean bound = super.bindTo(player, wand, x, y, z, side);
        if(!bound && (x != bindX || y != bindY || z != bindZ) && distTo(x, y, z) <= BIND_RANGE && (x != supertile.xCoord || y != supertile.yCoord || z != supertile.zCoord)) {
            bindX = x;
            bindY = y;
            bindZ = z;
            sync();
            return true;
        }
        return bound;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_BIND_X, bindX);
        cmp.setInteger(TAG_BIND_Y, bindY);
        cmp.setInteger(TAG_BIND_Z, bindZ);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        bindX = cmp.getInteger(TAG_BIND_X);
        bindY = cmp.getInteger(TAG_BIND_Y);
        bindZ = cmp.getInteger(TAG_BIND_Z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ChunkCoordinates getBinding() {
        return Minecraft.getMinecraft().thePlayer.isSneaking() && bindY != -1 ? new ChunkCoordinates(bindX, bindY, bindZ) : super.getBinding();
    }

    private double distTo(int x, int y, int z) {
        return Math.sqrt(Math.pow(x - supertile.xCoord, 2) + Math.pow(y - supertile.yCoord, 2) + Math.pow(z - supertile.zCoord, 2));
    }

    @Override
    public LexiconEntry getEntry() {
        return BotanicalFactory.entrySpectranthemumMagnum;
    }
}
