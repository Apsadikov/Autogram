package com.autogram.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private HashMap<String, String> errorList;

    public Validator() {
        errorList = new HashMap<>();
    }

    public HashMap<String, String> getErrorList() {
        return errorList;
    }

    public Specification value(String key, String value) {
        return new Specification(key, value);
    }

    public class Specification {
        private String value;
        private String key;
        private List<Predicate<String>> predicateList;
        private List<String> msgList;

        private Specification(String key, String value) {
            this.value = value;
            this.key = key;
            predicateList = new ArrayList<>();
            msgList = new ArrayList<>();
        }

        public Specification isRequired() {
            predicateList.add(s -> (s != null));
            return this;
        }

        public Specification length(int start, int finish) {
            predicateList.add(s -> (s.length() >= start && s.length() <= finish));
            return this;
        }

        public Specification isEmail() {
            predicateList.add(s -> {
                String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                return matcher.matches();
            });
            return this;
        }

        public Specification isPassword() {
            predicateList.add(s -> {
                String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,32}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);
                return matcher.matches();
            });
            return this;
        }

        public Specification customSpecification(Predicate<String> predicate) {
            predicateList.add(predicate);
            return this;
        }

        public Specification onError(String msg) {
            msgList.add(msg);
            return this;
        }

        public String validate() {
            int i = 0;
            for (Predicate<String> predicate : predicateList) {
                if (!predicate.test(value)) {
                    errorList.put(key, msgList.get(i));
                    return value;
                }
                i += 1;
            }
            return value;
        }
    }
}
