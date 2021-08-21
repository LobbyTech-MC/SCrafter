package cn.zimzaza4.slimefunzt.World;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VoidWorld extends ChunkGenerator {
    private SimplexOctaveGenerator noise;
    private SimplexOctaveGenerator fognoise;
    private SimplexOctaveGenerator landnoise;
    private SimplexOctaveGenerator webnoise;
    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData data = createChunkData(world);
        data.setRegion(0, 255,0, 16,256, 16, Material.BEDROCK);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                biome.setBiome(i, j, Biome.THE_VOID);
            }
        }
        if (noise==null){

            noise = new SimplexOctaveGenerator(world.getSeed(), 1);
            noise.setScale(0.009D);

        }
        if (fognoise==null) {
            fognoise = new SimplexOctaveGenerator(world.getSeed() + 193873, 1);
            fognoise.setScale(0.009D);

        }
        if (landnoise==null) {
            landnoise = new SimplexOctaveGenerator(world.getSeed()+19283737, 1);
            landnoise.setScale(0.01D);

        }
        if (webnoise==null) {
            webnoise = new SimplexOctaveGenerator(world.getSeed(), 1);
            webnoise.setScale(0.2D);
        }



        for (int x1 = 0; x1 < 16; x1++) {
            for (int z1 = 0; z1 < 16; z1++) {
                int realX = x * 16 + x1;
                int realZ = z * 16 + z1;
                double noiseValue = noise.noise(realX, realZ, 3D, 0.5D);

                int height = (int) (Math.abs(Math.pow(-(noiseValue*10), 2)*2.5));

                if (height<80) {

                for (int y =70; y > height-10 ; y--) {
                    data.setBlock(x1, y, z1, Material.BLACKSTONE);
                }
                data.setBlock(x1, 70 ,z1,Material.DIRT_PATH );


                }

                double noiseValue2 = landnoise.noise(realX, realZ, 3, 0.5D);
                int heig = (int) (noiseValue2 * 40D + 100D);
                if (heig<86) {

                    for (int y =170; y > heig+85 ; y--) {
                        data.setBlock(x1, y, z1, Material.STONE);
                    }
                    data.setBlock(x1, 171 ,z1,Material.GRASS_BLOCK );


                }

                double noiseV = fognoise.noise(realX, realZ, 1D, 0.5D);

                int fogr = (int) (noiseV * 40D + 100D);
                if (fogr > 120){

                    data.setBlock(x1, 151, z1, Material.GRAY_STAINED_GLASS);
                }

                double noiseweb = webnoise.noise(realX, realZ, 3D, 0.5D);
                if (noiseweb*40 >20){
                    data.setBlock(x1, 202, z1, Material.WHITE_STAINED_GLASS);
                }

            }
        }

        return data;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(@Nonnull World world){
        return Collections.singletonList(new VoidPopulator());
    }
}
