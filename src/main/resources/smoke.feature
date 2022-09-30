Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly



  Scenario Outline: Check searching system
    Given User opens '<homePage>' page
    And User search '<item>'
    Then User checks that search page contains '<item>'opened
    And User checks that search page was not empty
    Examples:
      | homePage                            | item       |
      | https://www.amazon.com              | headphones |
  Scenario Outline: Check changing location
    Given User opens '<homePage>' page
    Then User clicks change location button
    And Users choose location '<location>'
    And Users checks that location has changed
    Examples:
      | homePage                            | location       |
      | https://www.amazon.com              | Australia      |
  Scenario Outline: Check Main Menu Buttons
  Given User opens '<homePage>' page
  Then User checks that all menu buttons clicks in turn and opened relevant windows
   Examples:
    | homePage                            |
    | https://www.amazon.com              |

  Scenario Outline: Check adding product to cart
    Given User opens '<homePage>' page
    And User search '<item>'
    And User opens product '<productIndex>' page
    Then User checks that product add to cart
    And User checks that product title in cart equals title in product card
    And User checks that product price in cart equals price in product card
    Examples:
      | homePage                            |item                        |productIndex |
      | https://www.amazon.com              |QFM-00001                   |0            |

  Scenario Outline: Check that total product price in a cart changes when quantity is changes
    Given User opens '<homePage>' page
    And User search '<item>'
    And User opens product '<productIndex>' page
    Then User checks that product add to cart
    And User set quantity '<quantity>'
    And User checks that product price in cart equals price*'<quantity>'
    Examples:
      | homePage                            | item                         |productIndex |quantity|
      | https://www.amazon.com              | QFM-00001                    |0            |2       |



  Scenario Outline: Check that product page main fields contains
  Given User opens '<homePage>' page
  And User search '<item>'
  Then User opens product '<productIndex>' page
  And User checks that image visible
  And User checks that title visible
  And User checks that details visible

    Examples:
      | homePage                            | item     |productIndex|
      | https://www.amazon.com              | monitors |0           |

  Scenario Outline: Check Input Fields Registration Form
    Given User opens '<homePage>' page
    And User clicks SignIn
    And User clicks registration button
    Then User check that registration form is loaded
    And User checks field name visibility
    And User checks field email visibility
    And User checks field password visibility
    And Users checks field password check visibility
    Examples:
      | homePage                            |
      | https://www.amazon.com              |

  Scenario Outline: Check registration with incorrect filled data
    Given User opens '<homePage>' page
    And User clicks SignIn
    And User clicks registration button
    Then User check that registration form is loaded
    And User fills field name with '<name>'
    And User fills field email with '<email>'
    And User fills field password with '<password>'
    And Users fills field password check '<checkpassword>'
    And Users clicks button confirm and checks data
    Examples:
      | homePage                            | name     |email       |password  |checkpassword  |
      | https://www.amazon.com              | Vasya P  |123456      |123456    |123456         |
      | https://www.amazon.com              | Vasya P  |v@gmail.com |123456    |654321         |
      | https://www.amazon.com              |          |v@gmail.com |123456    |654321         |

  Scenario Outline: Check registration with correct filled data
    Given User opens '<homePage>' page
    And User clicks SignIn
    And User clicks registration button
    Then User check that registration form is loaded
    And User fills field name with '<name>'
    And User fills field email with '<email>'
    And User fills field password with '<password>'
    And Users fills field password check '<checkpassword>'
    And Users clicks button confirm and checks valid data
    Examples:
      | homePage                            | name     |email            |password  |checkpassword  |
      | https://www.amazon.com              | Vasya P  |vasya_p@gmail.com |123456    |123456         |






  Scenario Outline: Check filters at Gift Cards page
    Given User opens '<homePage>' page
    And User opens Gift Cards page
    And User choose filter number '<filterIndex>'
    Then User counts cards and compares with '<quantity>'
    Examples:
      | homePage                            | filterIndex       |quantity |
      | https://www.amazon.com              | 4                 |3        |

