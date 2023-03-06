package com.epam.mjc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> tokens = new StringSplitter().splitByDelimiters(signatureString, List.of(" ", "(", ")", ","));
        AccessModifier accessModifier;
        switch (tokens.get(0)) {
            case "private": accessModifier = AccessModifier.PRIVATE;
            break;
            case "protected": accessModifier = AccessModifier.PROTECTED;
            break;
            case "public": accessModifier = AccessModifier.PUBLIC;
            break;
            default: accessModifier = AccessModifier.DEFAULT;
            break;
        }
        Iterator<String> iterator = tokens.iterator();
        if (accessModifier != AccessModifier.DEFAULT) {
            iterator.next();
        }
        String returnType = iterator.next();
        String methodName = iterator.next();
        List<MethodSignature.Argument>  arguments = new ArrayList<>();
        while (iterator.hasNext()) {
            String type = iterator.next();
            String name = iterator.next();
            arguments.add(new MethodSignature.Argument(type, name));
        }
        MethodSignature parsedSignature = new MethodSignature(methodName, arguments);
        parsedSignature.setAccessModifier(accessModifier.lowercaseName());
        parsedSignature.setReturnType(returnType);
        return parsedSignature;
    }

    private enum AccessModifier {
        PRIVATE,
        DEFAULT,
        PROTECTED,
        PUBLIC;

        String lowercaseName() {
            return name().toLowerCase();
        }
    }
}
