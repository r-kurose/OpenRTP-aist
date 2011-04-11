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
 * @class ComFkSVC_impl
 * Example class implementing IDL interface ComFk
 */
class ComFkSVC_impl
 : public virtual POA_ComFk,
   public virtual PortableServer::RefCountServantBase
{
 private:
   // Make sure all instances are built on the heap by making the
   // destructor non-public
   //virtual ~ComFkSVC_impl();

 public:
  /*!
   * @brief standard constructor
   */
   ComFkSVC_impl();
  /*!
   * @brief destructor
   */
   virtual ~ComFkSVC_impl();

   // attributes and operations
   void set_tool(const Frame& frm);

};



#endif // TESTSERVICESVC_IMPL_H


