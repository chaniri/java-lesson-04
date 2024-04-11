package kr.easw.lesson04;

public class EncapsulationExample {
    public static void main(String[] args) {
        try {
            Car car = getCar();
            System.out.printf("차 이름: %s\n", car.getCarName());
            System.out.printf("연비: %.2fL/h", car.getRealFuelEfficiency());
            System.out.println("오답입니다.");
        } catch (Exception e) {
            System.out.println("연비 조작이 확인되었습니다.");
            System.out.println("정답입니다.");
        }
    }

    private static abstract class Car {
        private final String carName = "Car Prototype";

        private double realFuelEfficiency = 7.5;

        public String getCarName() {
            return carName;
        }

        public double getRealFuelEfficiency() {
            return realFuelEfficiency;
        }

        public void setRealFuelEfficiency(double realFuelEfficiency) {
            // 현재 연비보다 높은 값을 설정하려고 하면 오류 발생
            if (realFuelEfficiency > this.realFuelEfficiency) {
                throw new IllegalArgumentException("현재 연비보다 높은 값을 설정할 수 없습니다.");
            }
            this.realFuelEfficiency = realFuelEfficiency;
        }
    }

    private static class PerformanceManipulation extends Car {
        {
            // 성능 조작 클래스에서는 연비를 15.0으로 설정
            setRealFuelEfficiency(15.0);
        }

        @Override
        public String getCarName() {
            return "New Car";
        }
    }

    public static Car getCar() {
        return new PerformanceManipulation();
    }
}
