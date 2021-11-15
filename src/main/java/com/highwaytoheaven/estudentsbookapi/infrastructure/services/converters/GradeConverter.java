package com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.springframework.stereotype.Component;

@Component
public class GradeConverter {

  private final Map<GradeType, Converter> converters;

  {
    converters = HashMap.of(
        GradeType.LETTER, new LetterConverter(),
        GradeType.NUMERIC, new NumericConverter(),
        GradeType.PERCENTAGE, new PercentConverter()
    );
  }
  private final Map<String, Double> lettersMap;
  {
    lettersMap = HashMap.of(
            "celujący", 1.0,
            "+bardzo dobry", 0.90,
            "bardzo dobry", 0.85,
            "+dobry", 0.78,
            "dobry", 0.70,
            "+dostateczy", 0.61,
            "dostateczy", 0.50,
            "dopuszczający", 0.35,
            "niedostateczny", 0.0
    );
  }

  private final Map<String, Double> numericsMap;
  {
    numericsMap = HashMap.of(
            "6", 1.0,
            "+5", 0.90,
            "5", 0.85,
            "+4", 0.78,
            "4", 0.70,
            "+3", 0.61,
            "3", 0.50,
            "2", 0.35,
            "1", 0.0
    );
  }

  public Option<Double> convertValueToType(String s, GradeType type) {
    Option<Converter> converter = this.converters.get(type);

    if (converter.isEmpty()) {
      throw new IllegalStateException("Not defined grade");
    }
    return converter.get().convert(s);
  }

  public interface Converter {
    Option<Double> convert(String s);
  }

  public class LetterConverter implements Converter {

    @Override
    public Option<Double> convert(String s) {

      Option<Double> value = null;
      if(lettersMap.containsKey(s)){
        value = lettersMap.get(s);
      }

      return value;
    }
  }

  private class NumericConverter implements Converter {

    @Override
    public Option<Double> convert(String s) {

      Option<Double> value = null;
      if(numericsMap.containsKey(s)){
        value = numericsMap.get(s);
      }

      return value;
    }
  }

  private class PercentConverter implements Converter {

    @Override
    public Option<Double> convert(String s) { // eg. s == 70%

      String str = s.split("%")[0];
      double value = Double.parseDouble(str);
      value /= 100.0;

      return Option.of(value);
    }
  }
}
