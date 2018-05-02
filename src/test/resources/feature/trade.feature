Feature: Brickendon code challenge

  Scenario: Get trade details for the given date range
    Given parse master list of instrument which can be traded
    And parse trade transaction details file
    When input the trade date range as 2017-11-30 and 2017-12-02
    Then would expect traded instrument as IN1
    And would expect to display the instrument wise traded volume

  Scenario: Should not get trade details the given date range
    Given parse master list of instrument which can be traded
    And parse trade transaction details file
    When input the trade date range as 2017-12-05 and 2017-12-06
    Then would expect to display the instrument wise traded volume


  Scenario Outline: Get instrument details which are not traded for the given date range
    Given parse master list of instrument which can be traded
    And parse trade transaction details file
    When input the trade date range as <fromDate> and <toDate>
    Then the list of instruments which are not traded <instruments>
    Examples:
      | fromDate   | toDate     | instruments |
      | 2017-11-30 | 2017-12-02 | IN2,IN3     |
      | 2017-12-03 | 2017-12-04 | IN1,IN2,IN3 |
