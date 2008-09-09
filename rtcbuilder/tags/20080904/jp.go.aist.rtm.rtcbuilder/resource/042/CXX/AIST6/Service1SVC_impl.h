// -*-C++-*-
/*!
 * @file  Service1SVC_impl.h
 * @brief Service implementation header of Service1.idl
 *
 */

#include "Service1Skel.h"

#ifndef SERVICE1SVC_IMPL_H
#define SERVICE1SVC_IMPL_H
 
/*!
 * @class testSVC_impl
 * Example class implementing IDL interface test
 */
class testSVC_impl
 : public virtual POA_test,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~testSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   testSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~testSVC_impl();

   // attributes and operations
   void Set(const test::TestData& Data);

};



#endif // SERVICE1SVC_IMPL_H


