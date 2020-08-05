package steps

import utils.makeApiCall
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import model.Customer
import api.CustomerI.Companion.customerService
import org.junit.jupiter.api.Assertions.*
import retrofit2.Response
import support.World.Companion.instance as world

class CustomerSteps : En {

    init {

        DataTableType { entry: Map<String, String> ->
            Customer(entry["first_name"], entry["last_name"], null)
        }

        When("I create a new customer") { customerTable: DataTable ->
            val customers: List<Customer> = customerTable.asList(Customer::class.java)
            val res = makeApiCall(customerService().create(customers[0], world?.bearerToken.toString()))
            if (res.isSuccessful) {
                val createdCustomer: Customer = res.body() as Customer
                world!!.customerId = createdCustomer.id
            }
        }

        Then("I should be able to see customer's data") { customerTable: DataTable ->
            val expectedCustomers: List<Customer> = customerTable.asList(Customer::class.java)
            val res: Response<*> = world!!.latestResponse as Response<*>
            assertEquals(200, res.code())
            val getCustomerRes = makeApiCall(customerService().get(world?.customerId.toString(), world?.bearerToken.toString()))
            assertEquals(200, getCustomerRes.code())
            val actualCustomer: Customer = getCustomerRes.body() as Customer
            assertEquals(world?.customerId, actualCustomer.id)
            assertEquals(expectedCustomers[0].firstName, actualCustomer.firstName)
            assertEquals(expectedCustomers[0].lastName, actualCustomer.lastName)
        }
    }
}
