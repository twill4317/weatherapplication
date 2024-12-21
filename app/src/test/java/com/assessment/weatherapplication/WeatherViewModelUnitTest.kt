import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assessment.weatherapplication.AppModule
import com.assessment.weatherapplication.CityDataStore
import com.assessment.weatherapplication.Condition
import com.assessment.weatherapplication.Current
import com.assessment.weatherapplication.Location
import com.assessment.weatherapplication.MainActivity
import com.assessment.weatherapplication.RetrofitModule
import com.assessment.weatherapplication.WeatherApiService
import com.assessment.weatherapplication.WeatherRepository
import com.assessment.weatherapplication.WeatherResponse
import com.assessment.weatherapplication.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever
import kotlin.coroutines.coroutineContext

//import org.mockito.junit.jupiter.MockitoExtension
//import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
//@ExtendWith(MockitoExtension::class)  // This tells JUnit to use Mockito
class WeatherViewModelUnitTest {

    // Rule to allow LiveData to post updates synchronously in unit tests
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    val mainActivity: MainActivity = MainActivity()
    @Mock
    lateinit var mockWeatherRepository: WeatherRepository
    @Mock
    private lateinit var weatherViewModel: WeatherViewModel
    @Mock
    private lateinit var weatherApiService: WeatherApiService
    @Mock
    private lateinit var cityDataStore: CityDataStore

    @Before
    fun setUp() {
        // Set the main dispatcher for testing

        Dispatchers.setMain(Dispatchers.Unconfined)
        weatherApiService = RetrofitModule.provideService()
        cityDataStore = CityDataStore(AppModule.provideContext(mainActivity.application))
        mockWeatherRepository = WeatherRepository(weatherApiService, cityDataStore )

        // Initialize the ViewModel with the mocked repository
        weatherViewModel = WeatherViewModel(mockWeatherRepository)
    }

    @Test
    fun `test fetch weather success`() = runTest {
        // Arrange: Mock the repository's response
        val expectedWeather = WeatherResponse(
            Location("London", "United Kingdom", "2024-12-21 1:50"),
            Current(6.5, 43.7, 0.0, 86,
                Condition("Patchy rain nearby", "//cdn.weatherapi.com/weather/64x64/night/176.png"), 38.4, 3.6)
        )
        whenever(mockWeatherRepository.getWeather("London")).thenReturn(Result.success(expectedWeather))

        // Act: Call the ViewModel method
        weatherViewModel.saveSelectedCity("London")
        weatherViewModel.getWeather()

        // Assert: Verify the ViewModel's LiveData holds the correct weather data
        assertEquals(expectedWeather, weatherViewModel.weather.value)
    }

    @Test
    fun `test fetch weather failure`() = runTest {
        // Arrange: Mock a failure response
        val errorMessage = "Failed to fetch weather"
        whenever(mockWeatherRepository.getWeather("Test City")).thenReturn(Result.failure(Exception(errorMessage)))

        // Act: Call the ViewModel method
        weatherViewModel.saveSelectedCity("Test city")
        weatherViewModel.getWeather()

        // Assert: Ensure that the weather data remains null on failure
        assertEquals(null, weatherViewModel.weather.value)
    }
}
