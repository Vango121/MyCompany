package com.marcel.mycompany

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    WorkersFragmentTest::class
)
class TestSuite {
}