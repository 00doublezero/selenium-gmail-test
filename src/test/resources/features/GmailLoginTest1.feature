Feature: Login to Gmail
  Scenario: Login to Gmail
    Given user navigates to Gmail login page
    And he enter text 'vj1hao5g@gmail.com' in field 'login'
    And he press login Next button
    And he enter text 'WrerEDq9' in field 'password'
    And he press password Next button
    Then he can see 'vj1hao5g@gmail.com' in title of page