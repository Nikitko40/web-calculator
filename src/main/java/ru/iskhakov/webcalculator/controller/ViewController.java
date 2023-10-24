package ru.iskhakov.webcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iskhakov.webcalculator.entities.ResultEntity;
import ru.iskhakov.webcalculator.operations.Calculate;
import ru.iskhakov.webcalculator.repositories.ResultRepository;

import java.util.Date;

@Controller
public class ViewController {

    private final ResultEntity resultEntity;
    private final Calculate calculate;
    private final ResultRepository resultRepository;

    public ViewController(ResultRepository resultRepository, ResultEntity resultEntity, Calculate calculate) {
        this.resultRepository = resultRepository;
        this.resultEntity = resultEntity;
        this.calculate = calculate;
    }


    @RequestMapping("/")
    public String showMainPage() {
        return "MainPage";
    }

    @RequestMapping("/history")
    public String showOperationsHistory(Model model) {

        model.addAttribute("allResults", resultRepository.findAll());
        model.addAttribute("resultEntity", resultEntity);
        return "ResultsHistory";
    }

    @RequestMapping("/addition")
    public String additionAndSave(@RequestParam() String result, Model model) {

        double[] convertedResult = calculate.StringToDoubleArrayConvert(result);

        resultRepository.save(ResultEntity.builder()
                .result(calculate.additionNumbers(convertedResult))
                .date(new Date())
                .operationName("Сложение 2-х и более чисел")
                .build());
        model.addAttribute("result", calculate.additionNumbers(convertedResult));
        return "/OperationResult";
    }

    @RequestMapping("/multiply")
    public String multiplyAndSave(@RequestParam() String result, Model model) {

        double[] convertedResult = calculate.StringToDoubleArrayConvert(result);

        resultRepository.save(ResultEntity.builder()
                .result(calculate.multiplyNumbers(convertedResult))
                .date(new Date())
                .operationName("Умножение 2-х и более чисел")
                .build());
        model.addAttribute("result", calculate.multiplyNumbers(convertedResult));
        return "/OperationResult";
    }

    @RequestMapping("/addition-multiply")
    public String additionMultiplyAndSave(@RequestParam() String result, Model model) {

        double[] convertedResult = calculate.StringToDoubleArrayConvert(result);

        resultRepository.save(ResultEntity.builder()
                .result(calculate.multiplyAndAdditionNumbers(convertedResult))
                .date(new Date())
                .operationName("Умножение первых 2-х чисел и сложение с 3-м числом")
                .build());
        model.addAttribute("result", calculate.multiplyAndAdditionNumbers(convertedResult));
        return "/OperationResult";
    }

    @RequestMapping("/divide")
    public String divideAndSave(@RequestParam() String result, Model model) {

        double[] convertedResult = calculate.StringToDoubleArrayConvert(result);

        resultRepository.save(ResultEntity.builder()
                .result(calculate.divideNumbers(convertedResult))
                .date(new Date())
                .operationName("Деление 2-х и более чисел")
                .build());
        model.addAttribute("result", calculate.divideNumbers(convertedResult));
        return "/OperationResult";
    }
}
