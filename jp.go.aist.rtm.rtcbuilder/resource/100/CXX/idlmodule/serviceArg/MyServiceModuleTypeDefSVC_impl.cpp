// -*-C++-*-
/*!
 * @file  MyServiceModuleTypeDefSVC_impl.cpp
 * @brief Service implementation code of MyServiceModuleTypeDef.idl
 *
 */

#include "MyServiceModuleTypeDefSVC_impl.h"

/*
 * Example implementational code for IDL interface RTC::RTM::MyService
 */
MyServiceSVC_impl::MyServiceSVC_impl()
{
  // Please add extra constructor code here.
}


MyServiceSVC_impl::~MyServiceSVC_impl()
{
  // Please add extra destructor code here.
}


/*
 * Methods corresponding to IDL attributes and operations
 */
RTC::RTM::Time MyServiceSVC_impl::echo(const RTC::RTM::Time& msg, const char* str)
{
	RTC::RTM::Time result;
  // Please insert your code here and remove the following warning pragma
#ifndef WIN32
  #warning "Code missing in function <RTC::RTM::Time MyServiceSVC_impl::echo(const RTC::RTM::Time& msg, const char* str)>"
#endif
  return result;
}



// End of example implementational code



