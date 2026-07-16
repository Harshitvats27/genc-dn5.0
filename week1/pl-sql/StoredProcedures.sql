-- ============================================
-- Exercise 3: Stored Procedures
-- ============================================

-- --------------------------------------------
-- Scenario 1: Process monthly interest for
-- all savings accounts (1% interest rate)
-- --------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    CURSOR savings_cursor IS
        SELECT AccountID, Balance
        FROM Accounts
        WHERE AccountType = 'Savings';

    v_interest NUMBER;
    v_new_balance NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('   PROCESSING MONTHLY INTEREST');
    DBMS_OUTPUT.PUT_LINE('========================================');

    FOR acc_rec IN savings_cursor LOOP
        -- Calculate 1% interest
        v_interest := acc_rec.Balance * 0.01;
        v_new_balance := acc_rec.Balance + v_interest;

        -- Update account balance
        UPDATE Accounts
        SET Balance = v_new_balance,
            LastModified = SYSDATE
        WHERE AccountID = acc_rec.AccountID;

        -- Log the transaction
        INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
        VALUES (
            (SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions),
            acc_rec.AccountID,
            SYSDATE,
            v_interest,
            'Interest'
        );

        DBMS_OUTPUT.PUT_LINE('Account ID: ' || acc_rec.AccountID ||
            ' | Previous Balance: $' || acc_rec.Balance ||
            ' | Interest: $' || v_interest ||
            ' | New Balance: $' || v_new_balance);
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('Monthly interest processing complete.');
END;
/

-- --------------------------------------------
-- Scenario 2: Update employee bonus based on
-- department and bonus percentage parameter
-- --------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
    CURSOR emp_cursor IS
        SELECT EmployeeID, Name, Salary
        FROM Employees
        WHERE Department = p_department;

    v_bonus_amount NUMBER;
    v_new_salary NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('   UPDATING EMPLOYEE BONUSES');
    DBMS_OUTPUT.PUT_LINE('Department: ' || p_department);
    DBMS_OUTPUT.PUT_LINE('Bonus Percentage: ' || p_bonus_percent || '%');
    DBMS_OUTPUT.PUT_LINE('========================================');

    FOR emp_rec IN emp_cursor LOOP
        -- Calculate bonus amount
        v_bonus_amount := emp_rec.Salary * (p_bonus_percent / 100);
        v_new_salary := emp_rec.Salary + v_bonus_amount;

        -- Update employee salary
        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeID = emp_rec.EmployeeID;

        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_rec.Name ||
            ' | Current Salary: $' || emp_rec.Salary ||
            ' | Bonus: $' || v_bonus_amount ||
            ' | New Salary: $' || v_new_salary);
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('Bonus update complete.');
END;
/

-- --------------------------------------------
-- Scenario 3: Transfer funds between accounts
-- with sufficient balance check
-- --------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance NUMBER;
    v_dest_balance NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('========================================');
    DBMS_OUTPUT.PUT_LINE('   PROCESSING FUND TRANSFER');
    DBMS_OUTPUT.PUT_LINE('From Account: ' || p_from_account);
    DBMS_OUTPUT.PUT_LINE('To Account: ' || p_to_account);
    DBMS_OUTPUT.PUT_LINE('Amount: $' || p_amount);
    DBMS_OUTPUT.PUT_LINE('========================================');

    -- Get source account balance
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_from_account;

    -- Get destination account balance
    SELECT Balance INTO v_dest_balance
    FROM Accounts
    WHERE AccountID = p_to_account;

    -- Check sufficient balance
    IF v_source_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Insufficient balance!');
        DBMS_OUTPUT.PUT_LINE('Available: $' || v_source_balance ||
            ' | Requested: $' || p_amount);
        RETURN;
    END IF;

    -- Debit source account
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    -- Credit destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    -- Log transactions
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (
        (SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions),
        p_from_account,
        SYSDATE,
        p_amount,
        'Withdrawal'
    );

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (
        (SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions),
        p_to_account,
        SYSDATE,
        p_amount,
        'Deposit'
    );

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transfer successful!');
    DBMS_OUTPUT.PUT_LINE('Account ' || p_from_account || ' balance: $' || (v_source_balance - p_amount));
    DBMS_OUTPUT.PUT_LINE('Account ' || p_to_account || ' balance: $' || (v_dest_balance + p_amount));
END;
/
