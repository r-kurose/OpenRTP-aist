// -*-C++-*-
/*!
 * @file  TestServiceSVC_impl.h
 * @brief Service implementation header of TestService.idl
 *
 */

#include "TestServiceSkel.h"

#ifndef TESTSERVICESVC_IMPL_H
#define TESTSERVICESVC_IMPL_H
 
/*!
 * @class TestServiceSVC_impl
 * Example class implementing IDL interface TestService
 */
class TestServiceSVC_impl
 : public virtual POA_TestService,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~TestServiceSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   TestServiceSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~TestServiceSVC_impl();

   // attributes and operations
   Data getData();

};



#endif // TESTSERVICESVC_IMPL_H


