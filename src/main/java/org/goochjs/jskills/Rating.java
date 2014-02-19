package org.goochjs.jskills;

import static org.goochjs.jskills.numerics.MathUtils.square;
import java.util.Collection;
import org.goochjs.jskills.numerics.GaussianDistribution;
/** Container for a player's rating. **/

public class Rating {
	private static final int defaultConservativeStandardDeviationMultiplier = 3;
	private final double conservativeStandardDeviationMultiplier;
	/** The statistical mean value of the rating (also known as \u03bc). **/
	private final double mean;
	/** The standard deviation (the spread) of the rating. This is also known as \u03c3. **/
	private final double standardDeviation;
	/** The variance of the rating (standard deviation squared) **/
	public double getVariance() {
		return square(getStandardDeviation());
	}
	/** A conservative estimate of skill based on the mean and standard deviation. **/
	private final double conservativeRating;
	/**
     * Constructs a rating.
     * @param mean The statistical mean value of the rating (also known as \u03bc).
     * @param standardDeviation The standard deviation of the rating (also known as \u03c3).
     */
	public Rating(double mean, double standardDeviation) {
		this(mean, standardDeviation, defaultConservativeStandardDeviationMultiplier);
	}
	/**
     * Constructs a rating.
     * @param mean The statistical mean value of the rating (also known as \u03bc).
     * @param standardDeviation The number of standardDeviation to subtract from the mean to achieve a conservative rating.
     * @param conservativeStandardDeviationMultiplier The number of standardDeviations to subtract from the mean to achieve a conservative rating.
     */
	public Rating(double mean, double standardDeviation, double conservativeStandardDeviationMultiplier) {
		
		this.mean = mean;
		this.standardDeviation = standardDeviation;
		this.conservativeStandardDeviationMultiplier = conservativeStandardDeviationMultiplier;
		this.conservativeRating = mean - conservativeStandardDeviationMultiplier * standardDeviation;
	}
	
	public static Rating partialUpdate(Rating prior, Rating fullPosterior, double updatePercentage) {
		GaussianDistribution priorGaussian = new GaussianDistribution(prior);
		GaussianDistribution posteriorGaussian = new GaussianDistribution(fullPosterior);
		// From a clarification email from Ralf Herbrich:
		// "the idea is to compute a linear interpolation between the prior and
		// posterior skills of each player ... in the canonical space of
		// parameters"
		double precisionDifference = posteriorGaussian.getPrecision() - priorGaussian.getPrecision();
		double partialPrecisionDifference = updatePercentage * precisionDifference;
		double precisionMeanDifference = posteriorGaussian.getPrecisionMean() - priorGaussian.getPrecisionMean();
		double partialPrecisionMeanDifference = updatePercentage * precisionMeanDifference;
		GaussianDistribution partialPosteriorGaussion = GaussianDistribution.fromPrecisionMean(priorGaussian.getPrecisionMean() + partialPrecisionMeanDifference, priorGaussian.getPrecision() + partialPrecisionDifference);
		return new Rating(partialPosteriorGaussion.getMean(), partialPosteriorGaussion.getStandardDeviation(), prior.getConservativeStandardDeviationMultiplier());
	}
	
	@Override
	public String toString() {
		// As a debug helper, display a localized rating:
		return String.format("Mean(\u03bc)=%f, Std-Dev(\u03c3)=%f", mean, standardDeviation);
	}
	
	public static double calcMeanMean(Collection<Rating> ratings) {
		double ret = 0;
		for (Rating rating : ratings) ret += rating.mean;
		return ret / ratings.size();
	}
	
	@java.lang.SuppressWarnings("all")
	public double getConservativeStandardDeviationMultiplier() {
		return this.conservativeStandardDeviationMultiplier;
	}
	
	@java.lang.SuppressWarnings("all")
	public double getMean() {
		return this.mean;
	}
	
	@java.lang.SuppressWarnings("all")
	public double getStandardDeviation() {
		return this.standardDeviation;
	}
	
	@java.lang.SuppressWarnings("all")
	public double getConservativeRating() {
		return this.conservativeRating;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public boolean equals(final java.lang.Object o) {
		if (o == this) return true;
		if (!(o instanceof Rating)) return false;
		final Rating other = (Rating)o;
		if (!other.canEqual((java.lang.Object)this)) return false;
		if (java.lang.Double.compare(this.getConservativeStandardDeviationMultiplier(), other.getConservativeStandardDeviationMultiplier()) != 0) return false;
		if (java.lang.Double.compare(this.getMean(), other.getMean()) != 0) return false;
		if (java.lang.Double.compare(this.getStandardDeviation(), other.getStandardDeviation()) != 0) return false;
		if (java.lang.Double.compare(this.getConservativeRating(), other.getConservativeRating()) != 0) return false;
		return true;
	}
	
	@java.lang.SuppressWarnings("all")
	public boolean canEqual(final java.lang.Object other) {
		return other instanceof Rating;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		final long $conservativeStandardDeviationMultiplier = java.lang.Double.doubleToLongBits(this.getConservativeStandardDeviationMultiplier());
		result = result * PRIME + (int)($conservativeStandardDeviationMultiplier >>> 32 ^ $conservativeStandardDeviationMultiplier);
		final long $mean = java.lang.Double.doubleToLongBits(this.getMean());
		result = result * PRIME + (int)($mean >>> 32 ^ $mean);
		final long $standardDeviation = java.lang.Double.doubleToLongBits(this.getStandardDeviation());
		result = result * PRIME + (int)($standardDeviation >>> 32 ^ $standardDeviation);
		final long $conservativeRating = java.lang.Double.doubleToLongBits(this.getConservativeRating());
		result = result * PRIME + (int)($conservativeRating >>> 32 ^ $conservativeRating);
		return result;
	}
}