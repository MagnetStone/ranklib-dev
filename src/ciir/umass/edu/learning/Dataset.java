package ciir.umass.edu.learning;

import java.util.*;

/**
 * @author jfoley.
 */
public class Dataset extends AbstractList<RankList> {
  public List<String> inputFiles;
  public int maxFeature = 32;
  public int featureIncrease = 10;
  /** Max observed feature */
  public int maxFeaturePosition = 0;

  /** Datastorage */
  public List<RankList> samples;

  public Dataset() {
    this.inputFiles = new ArrayList<>();
    this.samples = new ArrayList<>();
  }

  public int getMaxFeaturePosition() {
    return maxFeaturePosition;
  }

  @Override
  public int size() {
    return samples.size();
  }

  @Override
  public RankList get(int i) {
    return samples.get(i);
  }

  @Override
  public RankList set(int i, RankList rl) {
    return samples.set(i, rl);
  }

  public PointBuilder makePointBuilder() {
    return new PointBuilder(this);
  }

  /** Dataset keeps track of largest point, so it avoids most re-allocations. */
  boolean resizeToFit(int fid) {
    boolean resize = false;
    while(fid >= maxFeature) {
      maxFeature += featureIncrease;
      resize = true;
    }
    maxFeaturePosition = Math.max(fid, maxFeaturePosition);
    return resize;
  }

  public int getNextFeatureId() {
    return maxFeaturePosition +1;
  }

  public int[] getFeatureArray() {
    int N = maxFeaturePosition;
    int[] data = new int[N];
    for (int i = 0; i < N; i++) {
      data[i] = i+1;
    }
    return data;
  }
}
