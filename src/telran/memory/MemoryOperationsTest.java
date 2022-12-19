package telran.memory;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class MemoryOperationsTest {
	private static final long MGB = 1024*1024;
	byte ar[];
	
	@Test
	@Disabled
	void maxMemoryTestTest() {
		int maxMemory = MemoryOperations.getMaxAvailableMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flagException = false;
		try {
			ar = new byte[maxMemory + 1];
		} catch(Throwable e) {
			flagException = true;
		}
		assertTrue(flagException);
	}
	@Test
	void standardMemoryMethodth( ) {
		Runtime runtime = Runtime.getRuntime();
		System.out.printf("Maximal memory JVM may requare from OS: %d, "
		+ "current total JVM memory:%d, current free JVM memory: %d ",
		runtime.maxMemory() / MGB, runtime.totalMemory() / MGB, runtime.freeMemory() / MGB);
		
	}
}
