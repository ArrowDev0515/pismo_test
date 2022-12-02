Download source code from github

	git clone https://github.com/royalflash5150/pismo-test.git



Go to directory you cloned.

	cd /pismo-test
  
  
  
Start docker compose.

	docker compose up



API:

BaseURL: http://localhost:8080

- Create an account

	POST /accounts
	
	Request Body:
	
		{
			"document_number": "12345678900"
		}
	
	Response: 
	
		account id(number)
		e.g: 1

	
- Retrieve the account information


	GET /accounts/:accountId
	
	Response Body:
	
		{
			"account_id": 1,
			"document_number": "12345678900"
		} 


- Create a transaction

	POST /transactions
	
	Request Body:
	
		{
			"account_id": 1,
			"operation_type_id": 4,
			"amount": 123.45
		}

	Response: 
	
		transaction id(number)
		e.g: 1


- Retrieve the transactions of account

	GET /transactions/:accountId
	
	Response Body:
	
		[
			{
				"amount": 123.45,
				"transaction_id": 1,
				"account_id": 1,
				"operation_type": "Credit Voucher",
				"event_date": "2022-12-03 05:58:55"
			},
			{
				"amount": 200.00,
				"transaction_id": 2,
				"account_id": 1,
				"operation_type": "Credit Voucher",
				"event_date": "2022-12-03 06:01:00"
			}
		]