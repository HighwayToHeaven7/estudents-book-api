package com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GradeConverter {

  private final Map<GradeType, Converter> converters;
  {
    converters = new HashMap<>();

    converters.put(GradeType.LETTER, new LetterConverter());
    converters.put(GradeType.NUMERIC, new NumericConverter());
    converters.put(GradeType.PERCENTAGE, new PercentConverter());
  }

  private final Map<String, Double> lettersMap;
  {
    lettersMap = new HashMap<>();

    lettersMap.put("celujący", 1.0);
    lettersMap.put("-celujący", 0.95);
    lettersMap.put("+bardzo dobry", 0.90);
    lettersMap.put("bardzo dobry", 0.85);
    lettersMap.put("-bardzo dobry", 0.80);
    lettersMap.put("+dobry", 0.75);
    lettersMap.put("dobry", 0.70);
    lettersMap.put("-dobry", 0.65);
    lettersMap.put("+dostateczny", 0.60);
    lettersMap.put("dostateczny", 0.55);
    lettersMap.put("-dostateczny", 0.50);
    lettersMap.put("+dopuszczający", 0.45);
    lettersMap.put("dopuszczający", 0.40);
    lettersMap.put("-dopuszczający", 0.35);
    lettersMap.put("+niedostateczny", 0.30);
    lettersMap.put("niedostateczny", 0.0);
  }

  private final Map<String, Double> numericsMap;
  {
    numericsMap = new HashMap<>();

    numericsMap.put("6", 1.0);
    numericsMap.put("-6", 0.95);
    numericsMap.put("+5", 0.90);
    numericsMap.put("5", 0.85);
    numericsMap.put("-5", 0.80);
    numericsMap.put("+4", 0.75);
    numericsMap.put("4", 0.70);
    numericsMap.put("-4", 0.65);
    numericsMap.put("+3", 0.60);
    numericsMap.put("3", 0.55);
    numericsMap.put("-3", 0.50);
    numericsMap.put("+2", 0.45);
    numericsMap.put("2", 0.40);
    numericsMap.put("-2", 0.35);
    numericsMap.put("+1", 0.30);
    numericsMap.put("1", 0.0);
  }

  public Optional<Double> convertValueToType(String s, GradeType type) throws IllegalArgumentException{
    Optional<Double> convertedValue = Optional.empty();

    if (!this.converters.containsKey(type))
      throw new IllegalArgumentException("Not defined grade type");

    Converter converter = this.converters.get(type);

    convertedValue = converter.convert(s);

    return convertedValue;
  }

  public interface Converter {
    Optional<Double> convert(String s) throws IllegalArgumentException;
  }

  public class LetterConverter implements Converter {

    @Override
    public Optional<Double> convert(String s) throws IllegalArgumentException{
      if(!lettersMap.containsKey(s))
        throw new IllegalArgumentException("Not defined grade");

      return Optional.of(lettersMap.get(s));
    }
  }

  private class NumericConverter implements Converter {

    @Override
    public Optional<Double> convert(String s) throws IllegalArgumentException{
      if(!numericsMap.containsKey(s))
        throw new IllegalArgumentException("Not defined grade");

      return Optional.of(numericsMap.get(s));
    }
  }

  private class PercentConverter implements Converter {

    @Override
    public Optional<Double> convert(String s) {

      String str = s.split("%")[0];

      double value = Double.parseDouble(str);
      value /= 100.0;

      return Optional.of(value);
    }
  }
}
