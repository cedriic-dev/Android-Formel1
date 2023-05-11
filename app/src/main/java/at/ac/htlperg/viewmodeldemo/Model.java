package at.ac.htlperg.viewmodeldemo;

import java.util.List;

import at.ac.htlperg.viewmodeldemo.model.Driver;

public class Model {
        private final List<Driver> drivers;

        public Model(List<Driver> users) {
                this.drivers = users;
        }
        public Model() {
                drivers = List.of();
        }

        public List<Driver> getDrivers() {
                return drivers;
        }
}