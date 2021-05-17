package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
    this.allDrivers.filter { driver ->
        this.trips.none { trip ->
            trip.driver == driver
        }
    }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    this.allPassengers
        .filter { passenger ->
            this.trips.filter { trip ->
                trip.passengers.any { tripPassenger ->
                    tripPassenger == passenger
                }
            }.count() >= minTrips
        }
        .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
    this.allPassengers.map { passenger ->
        passenger to trips.filter { trip ->
            trip.passengers.any { tripPassenger ->
                tripPassenger == passenger
            }
        }.filter { trips ->
            trips.driver == driver
        }.map { trip ->
            trip.driver
        }.groupBy { tripDriver ->
            tripDriver.name
        }
    }.filter { pair ->
        pair.second.count { entry ->
            entry.value.count() > 1
        } != 0
    }.map { pair ->
        pair.first
    }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
    this.allPassengers.map { passenger ->
        passenger to this.trips.filter { trip ->
            trip.passengers.any { tripPassenger ->
                tripPassenger == passenger
            }
        }.map { trip -> if (trip.discount == null) 0.0 else 1.0 }
    }.map { pair ->
        pair.first to (if (pair.second.count() != 0) pair.second.sum() / pair.second.count() else 0.0)
    }.filter { pair ->
        pair.second > 0.5
    }.map {
        it.first
    }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    return if (this.trips != null && this.trips.isNotEmpty()) {
        this.trips.map { trip ->
            trip.duration / 10
        }.groupBy {
            it
        }.maxByOrNull { entry ->
            entry.value.count()
        }?.let { entry -> IntRange(entry.key * 10, entry.key * 10 + 9) }
    } else {
        null
    }
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (this.trips != null && this.trips.isNotEmpty()) {
        val incomeByDriver = this.allDrivers.map { driver ->
            driver to this.trips.filter { trip ->
                trip.driver == driver
            }.map { trip ->
                trip.cost
            }.sum()
        }.sortedByDescending { pair ->
            pair.second
        }
        val paretoCount = (this.allDrivers.size * 0.2).toInt()
        if (paretoCount != 0) {
            val totalSum = incomeByDriver.map { pair ->
                pair.second
            }.sum()
            val paretoSum = incomeByDriver.take(paretoCount).map { pair ->
                pair.second
            }.sum()
            val paretoPrinciple = if (totalSum > 0) paretoSum / totalSum else 0.0
            return paretoPrinciple >= 0.8
        } else {
            return true
        }
    } else {
        return false
    }
}
