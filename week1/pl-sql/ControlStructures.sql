-- ============================================
-- Exercise 1: Control Structures
-- ============================================

-- --------------------------------------------
-- Scenario 1: Apply 1% discount to loan
-- interest rates for customers above 60
-- --------------------------------------------
DECLARE
    CURSOR loan_cursor IS
        SELECT l.LoanID, l.InterestRate, c.DOB
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID;

    v_age NUMBER;
BEGIN
    FOR loan_rec IN loan_cursor LOOP
        -- Calculate age from DOB
        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, loan_rec.DOB) / 12);

        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE LoanID = loan_rec.LoanID;

            DBMS_OUTPUT.PUT_LINE('Loan ID ' || loan_rec.LoanID ||
                ': Customer age ' || v_age ||
                ' - Discount applied. New rate: ' || (loan_rec.InterestRate - 1) || '%');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Loan ID ' || loan_rec.LoanID ||
                ': Customer age ' || v_age || ' - No discount (under 60)');
        END IF;
    END LOOP;

    COMMIT;
END;
/

-- --------------------------------------------
-- Scenario 2: Set IsVIP flag for customers
-- with balance over $10,000
-- --------------------------------------------

-- Add IsVIP column if it doesn't exist
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM user_tab_columns
    WHERE table_name = 'CUSTOMERS' AND column_name = 'ISVIP';
    IF v_count = 0 THEN
        EXECUTE IMMEDIATE 'ALTER TABLE Customers ADD IsVIP VARCHAR2(5) DEFAULT ''FALSE''';
    END IF;
END;
/

DECLARE
    CURSOR customer_cursor IS
        SELECT CustomerID, Name, Balance FROM Customers;

BEGIN
    FOR cust_rec IN customer_cursor LOOP
        IF cust_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE(cust_rec.Name ||
                ' (Balance: $' || cust_rec.Balance || ') - Promoted to VIP');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE(cust_rec.Name ||
                ' (Balance: $' || cust_rec.Balance || ') - Not eligible for VIP');
        END IF;
    END LOOP;

    COMMIT;
END;
/

-- --------------------------------------------
-- Scenario 3: Send reminders for loans due
-- within the next 30 days
-- --------------------------------------------
DECLARE
    CURSOR loan_due_cursor IS
        SELECT l.LoanID, l.EndDate, c.Name, c.CustomerID
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;

    v_days_remaining NUMBER;
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('   LOAN DUE REMINDERS (Next 30 Days)');
    DBMS_OUTPUT.PUT_LINE('========================================');

    FOR loan_rec IN loan_due_cursor LOOP
        v_count := v_count + 1;
        v_days_remaining := FLOOR(loan_rec.EndDate - SYSDATE);

        DBMS_OUTPUT.PUT_LINE('');
        DBMS_OUTPUT.PUT_LINE('Reminder for: ' || loan_rec.Name);
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || loan_rec.LoanID);
        DBMS_OUTPUT.PUT_LINE('Due Date: ' || TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY'));
        DBMS_OUTPUT.PUT_LINE('Days Remaining: ' || v_days_remaining);
        DBMS_OUTPUT.PUT_LINE('----------------------------------------');
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No loans due in the next 30 days.');
    END IF;
END;
/
