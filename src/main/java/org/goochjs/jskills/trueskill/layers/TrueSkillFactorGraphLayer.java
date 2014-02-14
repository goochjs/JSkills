package org.goochjs.jskills.trueskill.layers;

import org.goochjs.jskills.factorgraphs.Factor;
import org.goochjs.jskills.factorgraphs.FactorGraphLayer;
import org.goochjs.jskills.factorgraphs.Variable;
import org.goochjs.jskills.numerics.GaussianDistribution;
import org.goochjs.jskills.trueskill.TrueSkillFactorGraph;

public abstract class TrueSkillFactorGraphLayer<TInputVariable extends Variable<GaussianDistribution>, 
                                                TFactor extends Factor<GaussianDistribution>,
                                                TOutputVariable extends Variable<GaussianDistribution>>
    extends FactorGraphLayer
            <TrueSkillFactorGraph, GaussianDistribution, Variable<GaussianDistribution>, TInputVariable,
            TFactor, TOutputVariable> 
{
    public TrueSkillFactorGraphLayer(TrueSkillFactorGraph parentGraph)
    {
        super(parentGraph);
    }
}