package tdd

import com.twitter.scalding._
import com.twitter.scalding.Tsv

class ExampleJob(args: Args) extends Job(args) {
  import ExampleSchema._
  import tdd.ExampleWrapper._

  val dailyVisits = Tsv(args("input"), LOG_SCHEMA).read
    .logsAddDayColumn
    .logsCountVisits
    .write(Tsv(args("output1"), LOGS_DAILY_VISITS))

  val visitsPerDay = Tsv(args("input"), LOG_SCHEMA).read
    .logsAddDayColumn
    .logsCountVisits
    .logsJoinWithUsers(Tsv(args("users"), USER_SCHEMA).read)
    .write(Tsv(args("output2"),LOG_DAILY_WITH_ADDRESS))

}